package com.olegych

import beans.BeanInfo

/**
  */
class TransactionController(var model: TransactionModel = TransactionModel(1, progress = 0.5f)) {
  def start(done: => Unit) {
    new Thread {
      override def run = {
        (0 to 5).foreach { _ =>
          model.progress += 0.1f
          Thread.sleep(1000)
        }
        done
      }
      start
    }
  }
}

@BeanInfo case class TransactionModel(var price: Double, var progress: Float)