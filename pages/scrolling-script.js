
/* Called by body 'onload' event */
function init()
{
	onScrollEvent();  // Page could be loaded scrolled down
}

function onResizeEvent()
{
	onScrollEvent();  // Maintain correctly scaled position on resize
}

/* Should be called whenever the reader scrolls the page */
function onScrollEvent() 
{
	onScrollEvent();  // Maintain correctly scaled position on resize
}

/* Should be called whenever the reader scrolls or resizes the page */
function onScrollEvent()
{
	/* In case the browser doesn't support sticky positioning */
	if (!Modernizr.csspositionsticky)
	{
		document.getElementById("debug-counter").innerHTML = "Chrome";
		var mainTitle = document.getElementById("main-title");
		moveElementToSidebar("table-of-contents", mainTitle, !isScrolledIntoView(mainTitle));
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

/*  Find correct height of the document for any browser
 *  http://stackoverflow.com/a/1147768/5759072 
 */
function getBodyHeight()
{
	var body = document.body,
	html = document.documentElement;

	return Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
}

/* Fixed elements need to be dynamically moved compared to page-frame and main-title size */
function moveElementToSidebar(id, mainTitle, fixed)
{
	var element = document.getElementById(id),
		page = document.getElementById("page-frame");
		
	// Use counter for debugging
	//document.getElementById("debug-counter").innerHTML = ...;
		
	if (fixed == true)
	{
		element.style.top = "0";
		element.style.left = page.offsetWidth + page.offsetLeft - 0.5 ;
		element.style.position = "fixed";
	}
	else 
	{
		var style = window.getComputedStyle(mainTitle),
			margin = style.getPropertyValue("margin-bottom");
		
		element.style.top = mainTitle.offsetHeight + mainTitle.offsetTop;
		element.style.left = page.offsetWidth;
		element.style.position = "absolute";
	}
}
