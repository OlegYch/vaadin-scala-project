package com.olegych

import vaadin.scala._
import com.vaadin.navigator.{ViewDisplay, View, Navigator}
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent

class VaadinScalaApplication extends UI {
  val navigator = new Navigator(p, new ViewDisplay {
    def showView(view: View) {
      println(view)
      content = view.asInstanceOf[ComponentContainer]
    }
  })
  navigator.addView("", newView("This Vaadin app uses Scaladin!!!!!"))
  navigator.addView("hello", newView("HGello !!!This Vaadin app uses Scaladin!!!!!"))

  def newView(labelValue: String) = new VerticalLayout with View {
    margin = true
    components ++= Seq(Label(labelValue),
      Button("hekkoi", navigator.navigateTo("hello")))

    def enter(event: ViewChangeEvent) {}
  }
  p.setNavigator(navigator)
  navigator.navigate()
}