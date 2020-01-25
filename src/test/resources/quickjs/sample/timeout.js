setTimeout(() => {
	console.info('hello, world!');
}, 10);

let counter = 0;

setInterval(() => {
  console.info(counter++);
  if (counter > 4) {
	  timer.clearInterval(id);
  }
}, 500);

let timerId = setTimeout(() => {
	console.info('this line will not be print!');
}, 10);

clearTimeout(timerId);

let intervalId = setInterval(() => {
	console.info('this line will not be print too!');
}, 10);

clearInterval(intervalId);
