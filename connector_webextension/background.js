//handle the contentscript request
chrome.runtime.onMessage.addListener(function(requestOrig,sender,sendResponse){

   if(requestOrig.exits){
	       var hostName = requestOrig.hostAppName;

	   alert(hostName);
     //send request to native app
    chrome.runtime.sendNativeMessage(hostName, requestOrig, function(responseOrig) {
       if (chrome.runtime.lastError) {
         console.log("ERROR: " + chrome.runtime.lastError.message);
		 alert("ERROR: " + chrome.runtime.lastError.message);
       }else{
		   
		    alert("hhhhh:");
	   }
	    var nativeResponse = { response:responseOrig};
	  alert("gofd: " + JSON.stringify(nativeResponse));
     // console.log(responseOrig);
      chrome.tabs.query({active: true, currentWindow: true}, function(tabs){

          //send response to contentscript
         
          chrome.tabs.sendMessage(tabs[0].id, responseOrig);
          

      });

    });
  }

});
