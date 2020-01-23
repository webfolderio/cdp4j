let launcher = new Launcher();
let factory = launcher.launch();

let session = factory.create();
session.enableNetworkLog();

session.navigate('https://webfolder.io?cdp4j-js');
session.waitDocumentReady();
session.wait(1_000);

launcher.kill();
