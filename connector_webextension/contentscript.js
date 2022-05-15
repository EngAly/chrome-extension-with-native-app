 window.addEventListener("message", function(event) {
  
  alert(2222222);
  
  try {
	  
	   //var request = JSON.parse('{"exits":true,"name":"ddddd", "age":30, "hostAppName":"aly.ahmed"}'); 
 
    var request = JSON.parse(event.data);
    alert(request.hostAppName);
    //send request to background
    if(request.connector){
      chrome.runtime.sendMessage(request);
    }
  } catch(err) {
    console.log(err.message);
	alert(err);
  }

}, false);



//handle the background response
chrome.runtime.onMessage.addListener(function(nativeResponse,sender,sendResponse){
	
	 
	alert("return to content script ");
	
});




try {
	
	alert(11111111);
  //if(document.getElementById('cisConnectorHostAppName')){
   // var hostAppName = document.getElementById('cisConnectorHostAppName').dataset.connectorValue;
    //var hKey = document.getElementById('cisConnectorHostAppName').dataset.hKeyValue;
  //  var hName = document.getElementById('cisConnectorHostAppName').dataset.hNameValue;
   // var body = document.getElementById('request:cisConnectorRequestHidden').value;

    var jsonReq = { };
    // jsonReq[hKey] = hName;
    jsonReq.body = "/body";
    jsonReq.connector = true;
    jsonReq.hostAppName = "aly.ahmed";
    jsonReq.date = Date();
	jsonReq.exits=true;
    chrome.runtime.sendMessage(jsonReq);
//  }
} catch(err) {
	alert(err);
  console.log(err.message);
}