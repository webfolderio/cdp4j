package io.webfolder.cdp;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.*;

import static java.nio.file.attribute.PosixFilePermission.*;

public class ZipUtils {

    private static final PosixFilePermission[] decodeMap = {
            OTHERS_EXECUTE,
            OTHERS_WRITE,
            OTHERS_READ,
            GROUP_EXECUTE,
            GROUP_WRITE,
            GROUP_READ,
            OWNER_EXECUTE,
            OWNER_WRITE,
            OWNER_READ
    };

    public static void unpack(File archiveFile, File destFolder) throws IOException {
        ZipFile zf = new ZipFile(archiveFile);

        Map<File, String> symLinks = new HashMap<>();

        Iterator<ZipArchiveEntry> iterator = zf.getEntries().asIterator();

        //Top directory name we are going to ignore
        String parentDirectory = iterator.next().getName();

        //Iterate files & folders
        while (iterator.hasNext()) {
            ZipArchiveEntry entry = iterator.next();
            String name = entry.getName().substring(parentDirectory.length());
            File outputFile = new File(destFolder, name);

            if (entry.isUnixSymlink()) {
                symLinks.put(outputFile, zf.getUnixSymlink(entry));
            } else if (!entry.isDirectory()) {
                if (!outputFile.getParentFile().isDirectory()) {
                    outputFile.getParentFile().mkdirs();
                }

                IOUtils.copy(zf.getInputStream(entry), new FileOutputStream(outputFile));
            }

            // Set permission
            if (!entry.isUnixSymlink() && outputFile.exists())
                try {
                    Files.setPosixFilePermissions(
                            outputFile.toPath(),
                            modeToPosixPermissions(entry.getUnixMode())
                    );
                } catch (Exception ignored) {
                }
        }

        for (Map.Entry<File, String> entry : symLinks.entrySet())
            try {
                Path source = Paths.get(entry.getKey().getAbsolutePath());
                Path target = source.getParent().resolve(entry.getValue());
                if (!source.toFile().exists())
                    Files.createSymbolicLink(
                            source,
                            target
                    );
            } catch (Exception ignored) {
            }

    }

    private static Set<PosixFilePermission> modeToPosixPermissions(final int mode) {
        int mask = 1;

        Set<PosixFilePermission> perms = EnumSet.noneOf(PosixFilePermission.class);
        for (PosixFilePermission flag : decodeMap) {
            if ((mask & mode) != 0) {
                perms.add(flag);
            }
            mask = mask << 1;
        }

        return perms;
    }

}
