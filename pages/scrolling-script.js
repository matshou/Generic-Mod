
/* Called by body 'onload' event */
function init()
{
	onScrollEvent();  // Page could be loaded scrolled down
}

/* Should be called whenever the reader scrolls the page */
function onScrollEvent() 
{
	var mainTitle = document.getElementById("main-title");
	var	toc = document.getElementById("table-of-contents");

	if (!isScrolledIntoView(mainTitle))
	{
		toc.removeAttribute("style");
	    toc.className = "fixed";
		moveElementToSidebar("table-of-contents", mainTitle, true);
	}
	else
	{
		toc.removeAttribute("class");
		toc.style.position = "absolute";
		moveElementToSidebar("table-of-contents", mainTitle, false);
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
	
	if (fixed == true)
		element.style.left = page.offsetWidth + page.offsetLeft;
	
	else element.style.left = page.offsetWidth;
}
