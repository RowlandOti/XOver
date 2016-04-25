package com.rowland.auction.data.dashboardfeature.exception;

/**
 * Exception throw by the application when a User search can't return a valid result.
 */

public class BidNotFoundException extends Exception {

  public BidNotFoundException() {
    super();
  }

  public BidNotFoundException(final String message) {
    super(message);
  }

  public BidNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public BidNotFoundException(final Throwable cause) {
    super(cause);
  }
}
