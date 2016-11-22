
var activeHeading = null;

/* Called by body 'onload' event */
function init()
{
	activeHeading = getTableOfContentsList()[0];
	activeHeading.className = "active";
	
	onScrollEvent();  // Page could be loaded scrolled down
	
	// Use counter for debugging
	//document.getElementById("debug-counter").innerHTML = ...;
}

function onResizeEvent()
{
	onScrollEvent();  // Maintain correctly scaled position on resize
}

/* Should be called whenever the reader scrolls or resizes the page */
function onScrollEvent()
{
	/* In case the browser doesn't support sticky positioning */
	if (!Modernizr.csspositionsticky)
	{
		var mainTitle = document.getElementById("main-title");
		moveElementToSidebar("table-of-contents", mainTitle, !isScrolledIntoView(mainTitle));
	}
	
	var frame = document.getElementById("page-frame"),
		contents = getTableOfContentsList();
	
	/* 
	 *  Iterate through every TOC list element and figure out 
	 *	if it qualifies to be an active heading. 
	 */
	var lastActive = null;
	for (i = 0; i < contents.length; i++)
	{	
		var heading = document.getElementById(String(contents[i].getAttribute("href")).slice(1)),
			margin = parseInt(window.getComputedStyle(heading).getPropertyValue("margin-top"), 10);
		
		if (getDistanceFromDocument(heading).y - margin <= 0)
			lastActive = contents[i];
	}
	
	/* Change active heading only if it actually should be changed */
	if (lastActive != null && lastActive != activeHeading)
	{
		activeHeading.removeAttribute("class");
		activeHeading = lastActive;
		activeHeading.className = "active";
	}
}
/*  Is the document element visible to the reader, based on current page position via scrolling */
function isScrolledIntoView(elem)
{
	var docViewTop = document.body.scrollTop,
	docViewBottom = docViewTop + getBodyHeight();

	var elemTop = elem.offsetTop,
	elemBottom = elemTop + elem.offsetHeight;
	
	return ((elemBottom >= docViewTop) && (elemTop <= docViewBottom));
}

/* Fixed elements need to be dynamically moved compared to page-frame and main-title size */
function moveElementToSidebar(id, mainTitle, fixed)
{
	var element = document.getElementById(id),
		frame = document.getElementById("page-frame"),
		page = document.getElementById("main-page");
		
	if (fixed == true)
	{	
		var frameEndLeft = frame.offsetLeft + frame.offsetWidth;
		element.style.left = frameEndLeft - element.offsetWidth;
        element.className = "fixed";
	}
	else 
	{
		element.style.position = "static";
	}
}

/*  Find correct height of the document for any browser
 *  http://stackoverflow.com/a/1147768/5759072 
 */
function getBodyHeight()
{
	var body = document.body,
	html = document.documentElement;

	return Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
}

/* Function borrowed from an answer in an StackOverflow thread:
 * http://stackoverflow.com/a/24829409/5759072
 */
function getDistanceFromDocument(element) 
{
    var xPosition = 0, yPosition = 0;
    while(element) 
	{
        xPosition += (element.offsetLeft - element.scrollLeft + element.clientLeft);
        yPosition += (element.offsetTop - element.scrollTop + element.clientTop);
        element = element.offsetParent;
    }

    return { x: xPosition, y: yPosition };
}

/* Get ONLY link list entries from TOC in sidebar */
function getTableOfContentsList()
{
	return document.getElementById("table-of-contents").getElementsByTagName("a");
}
