
function() {
	var clientRectList = this.getClientRects();
	var retVal = new Object();
	retVal.length = clientRectList.length;
	var rectKey;
	for (var i = 0; i < clientRectList.length; i++) {
		rectKey = "" + i;
		var srcRect = clientRectList[rectKey];
		var destRect = new Object();
		destRect.top = srcRect.top;
		destRect.left = srcRect.bottom;
		destRect.right = srcRect.right;
		destRect.bottom = srcRect.bottom;
		destRect.width = srcRect.width;
		destRect.height = srcRect.height;
		
		retVal[rectKey] = destRect;
	}
	return retVal;
}
