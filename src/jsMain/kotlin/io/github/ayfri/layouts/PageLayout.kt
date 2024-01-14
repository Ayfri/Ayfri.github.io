package io.github.ayfri.layouts

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.core.rememberPageContext
import io.github.ayfri.AppStyle
import io.github.ayfri.Footer
import io.github.ayfri.header.Header
import io.github.ayfri.setCanonical
import io.github.ayfri.setTitle
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Style
import org.jetbrains.compose.web.dom.Main
import web.url.URL

@Composable
fun PageLayout(title: String, content: @Composable () -> Unit) {
	Style(AppStyle)
	setTitle("$title - Pierre Roy")

	Header()

	val context = rememberPageContext()
	val currentStub = context.route
	val url = URL(currentStub.path)
	url.search = ""
	url.hash = ""
	if (!url.pathname.endsWith("/")) url.pathname += "/"

	setCanonical("https://ayfri.com$url")

	Main({
		id("main")
	}) {
		content()
	}

	Footer()

	window.scroll(0.0, 0.0)
}
