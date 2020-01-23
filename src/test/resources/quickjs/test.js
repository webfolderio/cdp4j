let launcher = new Launcher();
let factory = launcher.launch();

let session = factory.create();

session.navigate('https://webfolder.io');
session.waitDocumentReady();

let body = session.getDOMSnapshot();
console.info(body);

launcher.kill();