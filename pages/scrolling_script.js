/* Should be called whenever the reader scrolls the page. */
function onScrollEvent() 
{
	if (! isScrolledIntoView(document.getElementById("anchor")))
	{
		// anchor div isn't visible in view so apply new style to follow div to follow on scroll
		document.getElementById("contents").className = "table-of-contents toc-fixed";
	}
	else
	{
		// anchor div is visible in view so apply default style back to follow div to place in default position
		document.getElementById("contents").className = "table-of-contents toc-absolute";
	}
}
/*  Is the document element visible to the reader, based on current page position via scrolling. */
function isScrolledIntoView(elem)
{
	var docViewTop = document.body.scrollTop,
	docViewBottom = docViewTop + getBodyHeight(),

	elemTop = elem.offsetTop,
	elemBottom = elemTop + elem.offsetHeight;

	return ((elemBottom >= docViewTop) && (elemTop <= docViewBottom));
}

/*  Find correct height of the document for any browser.
 *  http://stackoverflow.com/a/1147768/5759072 
 */
function getBodyHeight()
{
	var body = document.body,
	html = document.documentElement;

	return Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
}