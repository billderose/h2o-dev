package water.api;

import water.H2OError;
import water.util.DocGen;
import water.util.IcedHashMap;

import java.util.Arrays;

/**
 * Schema which represents a back-end error which will be returned to the client.  Such
 * errors may be caused by the user (specifying an object which has been removed) or due
 * to a failure which is out of the user's control.
 */
public class H2OErrorV1 extends Schema<H2OError, H2OErrorV1> {

  @API(help="Milliseconds since the epoch for the time that this H2OError instance was created.  Generally this is a short time since the underlying error ocurred.", direction=API.Direction.OUTPUT)
  public long timestamp;

  @API(help="Error url", direction=API.Direction.OUTPUT)
  String error_url;

  @API(help="Message intended for the end user (a data scientist).", direction=API.Direction.OUTPUT)
  public String msg;

  @API(help="Potentially more detailed message intended for a developer (e.g. a front end engineer or someone designing a language binding).", direction=API.Direction.OUTPUT)
  public String dev_msg;

  @API(help="HTTP status code for this error.", direction=API.Direction.OUTPUT)
  public int http_status;

//  @API(help="Unique ID for this error instance, so that later we can build a dictionary of errors for docs and I18N.", direction=API.Direction.OUTPUT)
//  public int error_id;

  @API(help="Any values that are relevant to reporting or handling this error.  Examples are a key name if the error is on a key, or a field name and object name if it's on a specific field.", direction=API.Direction.OUTPUT)
  public IcedHashMap values;

  @API(help="Exception type, if any.", direction=API.Direction.OUTPUT)
  public String exception_type;

  @API(help="Raw exception message, if any.", direction=API.Direction.OUTPUT)
  public String exception_msg;

  @API(help="Stacktrace, if any.", direction=API.Direction.OUTPUT)
  public String[] stacktrace;

  @Override public DocGen.HTML writeHTML_impl( DocGen.HTML ab ) {
    ab.bodyHead();

    if (0 == http_status)
      ab.title("H2O Error");
    else
      ab.title(H2OError.httpStatusHeader(http_status));
    ab.p("<div class='alert alert-error'>").p(msg).p("</div>");
    if (null != stacktrace)
      ab.p(Arrays.toString(stacktrace));

    return ab.bodyTail();
  }

}
