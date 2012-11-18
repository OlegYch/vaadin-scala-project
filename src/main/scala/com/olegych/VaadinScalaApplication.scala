package com.olegych

import vaadin.scala._
import com.vaadin.navigator.{ViewDisplay, View, Navigator}
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent

class VaadinScalaApplication extends UI {

  case class TabView(name: String) extends VerticalLayout with View {
    def enter(event: ViewChangeEvent) {
      //showing that state persists and lazy load
      components ++= Seq(
        Label(name)
        , Button(caption = name, clickListener = Notification.show("Clicked " + name))
      )
      Notification.show("Entered " + name + " with params " + event.getParameters)
    }
  }

  val views = Map("" -> TabView("Main"), "hello" -> TabView("Hello"))
  val root = new VerticalLayout {
    val menu = add(new MenuBar {
      views.foreach {
        case (url, view@TabView(name)) =>
          addItem(name, _ => navigator.navigateTo(url + "/param123"))
          navigator.addView(url, view)
      }
    })
    val content = add(new VerticalLayout())
  }
  content = root
  lazy val navigator: Navigator = new Navigator(p, new ViewDisplay {
    def showView(view: View) {
      root.content.removeAllComponents()
      root.content.add(view.asInstanceOf[Component])
    }
  })

  p.setNavigator(navigator)
  navigator.setErrorView(TabView("Error"))
  navigator.navigate()
}