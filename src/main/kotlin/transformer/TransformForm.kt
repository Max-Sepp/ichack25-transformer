package org.example.transformer

import org.example.ai.getLabelForInput
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.util.logging.Logger

const val NO_INPUT_LABEL = "no-input-label"

class TransformNoInputLabel(private val logger: Logger) : Transformer {
    override fun transformAll(document: Document): List<Transformation> = TODO("Not yet implemented")


    // Precondition: passed in input element does not have a label in the form
    // Response transformation returns the label plus the input
    override fun transform(element: Element): Transformation {
        require(element.tagName() == "input") {"element is not a input element"}

        val inputId: String = element.attr("id")
        val inputType: String = element.attr("type")
        val inputName: String = element.attr("name")
        val inputValue: String = element.attr("value")

        val inputLabel: String = getLabelForInput(
            id = inputId,
            type = inputType,
            name = inputName,
            value = inputValue,
        )

        val newElementString: String = inputLabel + "\n" + element.text()

        return Transformation(NO_INPUT_LABEL, element.toString(), newElementString)
    }
}