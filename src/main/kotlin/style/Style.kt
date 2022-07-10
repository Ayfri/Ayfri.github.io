package style

import org.jetbrains.compose.web.css.*

object CSSVariables : StyleSheet() {
	val navbarHeight by variable<CSSNumeric>()
}

object AppStyle : StyleSheet() {
	const val navbarColor = "#363636"
	const val navbarColorSelected = "#2b2b2b"
	const val footerColor = "#222222"
	const val footerLinkHover = "#cccccc"
	
	init {
		"body" {
			fontFamily("'Roboto'", "sans-serif")
			margin(0.px)
		}
		
		"a" style {
			textDecoration("none")
			property("transition", "color .2s ease-in-out")
		}
	}
	
	val navbar by style {
		CSSVariables.navbarHeight(5.cssRem)
		
		alignItems(AlignItems.Center)
		backgroundColor(Color(navbarColor))
		display(DisplayStyle.Flex)
		justifyContent(JustifyContent.SpaceBetween)
		position(Position.Fixed)
		size(CSSVariables.navbarHeight.value(), 100.percent)
		
		"i" style {
			fontSize(CSSVariables.navbarHeight.value() * .6)
		}
	}
	
	val navbarPart by style {
		alignItems(AlignItems.Center)
		color(Color.white)
		display(DisplayStyle.Flex)
		justifyContent(JustifyContent.Center)
	}
	
	val navbarLinks by style {
		"a" style {
			color(Color.white)
			height(100.percent)
			display(DisplayStyle.InlineBlock)
			lineHeight(CSSVariables.navbarHeight.value())
			padding(0.px, 2.cssRem)
			
			group(self + className("active"), hover(self)) style {
				backgroundColor(Color(navbarColorSelected))
			}
		}
	}
	
	val navbarGithub by style {
		borderRadius(1.5.cssRem)
		gap(1.cssRem)
		marginRight(2.cssRem)
		padding(.5.cssRem, 1.cssRem)
		
		hover(self) style {
			backgroundColor(Color(navbarColorSelected))
		}
	}
	
	val footer by style {
		backgroundColor(Color(footerColor))
		position(Position.Fixed)
		bottom(0.px)
		color(Color.white)
		padding(1.cssRem, 0.px)
		textAlign("center")
		width(100.percent)
		
		className("top") style {
			alignItems(AlignItems.Center)
			display(DisplayStyle.Flex)
			gap(2.cssRem)
			justifyContent(JustifyContent.Center)
			listStyle("none")
			padding(0.px)
			
			"a" style {
				color(Color.white)
				fontSize(2.5.cssRem)
				
				hover(self) style {
					color(Color(footerLinkHover))
				}
			}
		}
	}
}
