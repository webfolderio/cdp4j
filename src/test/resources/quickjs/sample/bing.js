let launcher = new Launcher();
let factory = launcher.launch();

let session = factory.create();
session.navigate('https://webfolder.io');

session.waitDocumentReady();

session.sendKeys('WebFolder OÜ cdp4j');
session.sendEnter();

session.wait(1_000);

let docHtml = session.getDOMSnapshot();
console.info(docHtml);
