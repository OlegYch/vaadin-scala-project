package com.olegych

import vaadin.scala._
import vaadin.scala.Notification.Type

/**
  */
class TransactionPage(controller: TransactionController = new TransactionController) extends CustomComponent {
  compositionRoot = new VerticalLayout {
    //see also com.vaadin.data.fieldgroup.BeanFieldGroup
    val form = new Form {
      caption = "Hello"
      val beanItem = new BeanItem(model) {
        item = this
        visibleItemProperties = propertyIds().filter(_ != "progress")
      }
      field("price").foreach { p =>
        p.required = true
        p.validators += (_ match {
          case Some(v: Double) if v >= 10 => Valid
          case _ => Invalid("price should be >= 10" :: Nil)
        })
      }
    }
    components ++=
        Button("check", Notification.show(model.toString)) ::
            Button("start", validated(form)(controller.start(done))) ::
            form ::
            new ProgressIndicator {property = form.beanItem.property("progress")} ::
            Nil
  }

  def validated(form: Form)(action: => Unit) {
    form.validate match {
      case Valid => action
      case Invalid(reasons) => Notification.show(reasons.toString, Type.Error)
    }
  }

  def done = {
    Notification.show("Done", Type.Tray)
  }

  def model = controller.model
}
