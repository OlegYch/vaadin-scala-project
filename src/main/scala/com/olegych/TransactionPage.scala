package com.olegych

import vaadin.scala._
import vaadin.scala.Notification.Type

class TransactionPage(controller: TransactionController = new TransactionController) extends BasePage {
  model.params = params
  compositionRoot = new VerticalLayout {
    val form = new Form {
      caption = 'hello
      val beanItem = new BeanItem(model) {
        item = this
        visibleItemProperties = propertyIds().filter(_ != "progress")
      }
      field("price").foreach { p =>
        p.required = true
        p.validators += >=(10.0)
      }
    }
    components ++=
        Button('check, Notification.show(model.toString)) ::
            Button('start, validated(form)(controller.start(done))) ::
            form ::
            new ProgressIndicator {property = form.beanItem.property("progress")} ::
            Nil
  }
  def done = Notification.show('done, Type.Tray)
  def model = controller.model
}
