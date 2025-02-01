package org.example

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

fun main() {
    val html = """
        <html>
            <body>
                <div id="content">
                    <p>Example paragraph.</p>
                    <a href="https://example.com">Example link</a>
                </div>
            </body>
        </html>
    """

    val document: Document = Jsoup.parse(html)
    val contentDiv: Element = document.getElementById("content")!!

    // Convert the element to its HTML string representation
    val htmlString: String = contentDiv.outerHtml()

    // Print the HTML string
    println(htmlString)
}