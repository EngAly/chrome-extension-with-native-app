/*
 * Copyright (c) ISOFT 2013.
 * Ferdous Tower (Takreer Building) , Salam Street
 * Abu Dhabi, United Arab Emirates
 * P.O. Box: 32326
 * All Rights Reserved.
 *
 */



  //Inject script to page
try {
  var s = document.createElement('script');
  s.src = chrome.extension.getURL('connectorExtension.js');
  (document.head||document.documentElement).appendChild(s);
  s.onload = function() {
      s.parentNode.removeChild(s);
  };
} catch(err) {
    console.log(err.message);
}
