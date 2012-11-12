package com.olegych

import vaadin.scala._

class VaadinScalaApplication extends Application("Vaadin Scala project") {
	override val main: ComponentContainer = new VerticalLayout {
		margin = true
		components += Label("This Vaadin app uses Scaladin!!!!!")
	}
}