let launcher = new Launcher({
	headless: true
});
let factory = launcher.launch();

let session = factory.create();
session.enableNetworkLog();

session.navigate('https://webfolder.io?cdp4j-js-headless');
session.waitDocumentReady();

console.info(session.getText('//body').value);

launcher.kill();
