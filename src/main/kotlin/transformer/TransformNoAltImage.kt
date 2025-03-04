package org.example.transformer

import org.example.ai.getImageAltText
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.util.logging.Level
import java.util.logging.Logger

const val NO_ALT_IMAGE_TYPE = "no-alt-image"

class TransformNoAltImage(private val logger: Logger) : Transformer {
    override fun transformAll(document: Document): List<Transformation> =
        document
            .select("img")
            .filter { !it.hasAttr("alt") }
            .map { transform(it) }


    override fun transform(element: Element): Transformation {
        require(element.tagName() == "img") { "Must enter a img tag" }
        require(!element.hasAttr("alt")) { "Must not have an alt" }

        val imageLink: String = element.attr("src")

        logger.log(Level.INFO, "Image link: $imageLink")

        val imageAltText: String = getImageAltText(imageLink)

        val newElement: Element = element.clone()

        newElement.attr("alt", imageAltText)

        return Transformation(NO_ALT_IMAGE_TYPE, element.toString(), newElement.toString())
    }
}

