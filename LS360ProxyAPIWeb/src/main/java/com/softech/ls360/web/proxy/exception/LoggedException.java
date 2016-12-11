package com.softech.ls360.web.proxy.exception;

/**
 * As you well know by now, application errors happen. You can’t prevent them completely. Eventually, something goes wrong and your 
 * application does not function properly. Usually, this causes a thrown exception. However, hiding all errors from users is not an 
 * acceptable alternative to displaying all error stack traces to users. When something goes wrong, users need to know. You should log 
 * technical details, but you should display a useful error message for users to help them understand what went wrong in the least 
 * technical terms possible. This error message must also be localized. You do not want this internationalization to affect the messages
 * written to your logs, just what displays to the user.
 * 
 * When an exception of some expected (but not wanted) type occurs, such as a SQLException when executing SQL statements using JDBC, the
 * natural tendency is to catch the exception and log it. This is okay, but you still need to report that error message to the user 
 * somehow. You could rethrow the exception, but then how does a higher layer in the application know that it has already been logged? 
 * Also if you simply rethrow the exception, how does a useful error message get presented to the user? A view catching an exception 
 * thrown three layers down, after all, has no idea in what context the exception was thrown, and therefore has no ability to create a 
 * useful error message for it.
 * 
 * To tackle the first problem, you can create your own custom exception and throw it instead of rethrowing the original exception. You 
 * might name it LoggedException and then institute a policy that LoggedExceptions should never be logged and should be rethrown if 
 * caught. All the LoggedException constructors require that you include the underlying exception as the cause. This certainly solves the
 * first problem, but it doesn’t solve the second.
 * 
 * A good way to tackle the second problem is to make LoggedException implement MessageSourceResolvable. Then, it contains its own error
 * code, default message, and arguments that you can use to internationalize display of the exception. However, if you think about this 
 * for a minute, you will quickly realize that some circumstances require you to throw internationalized exceptions without first 
 * catching an underlying exception. So, what you really need is an InternationalizedException that just implements 
 * MessageSourceResolvable, and a LoggedException that extends it.
 * 
 * @author basit.ahmed
 *
 */
public class LoggedException extends InternationalizedException {
	
	private static final long serialVersionUID = 1L;

	public LoggedException(Throwable cause, String errorCode, Object... arguments) {
		this(cause, errorCode, null, arguments);
	}

	public LoggedException(Throwable cause, String errorCode, String defaultMessage, Object... arguments) {
		super(cause, errorCode, defaultMessage, arguments);
	}
	
}
