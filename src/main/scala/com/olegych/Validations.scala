package com.olegych

import vaadin.scala._
import vaadin.scala.Notification.Type

/**
  */
trait Validations extends Translation {self: Component =>
  def validated(form: Form)(action: => Unit) {
    form.validate match {
      case Valid => action
      case Invalid(reasons) => Notification.show(reasons.toString, Type.Error)
    }
  }

  def >=[T](bound: T)(implicit ord: Ordering[T]): Option[Any] => Validation = {
    import ord._
    v =>
      v match {
        case Some(v: T) if v >= bound => Valid
        case _ => Invalid(Symbol("should be greater %s") -> bound :: Nil)
      }
  }
}
