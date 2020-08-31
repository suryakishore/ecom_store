package com.app.delivxstore.utility;

import android.widget.TextView;
import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import com.app.ecomstore.util.EcomUtil;
import io.reactivex.Observable;

/**
 * RxTextView compatible to rxjava2
 */

public class RxTextView {

  /**
   * Create an observable of character sequences for text changes on {@code view}.
   * <p>
   * <em>Warning:</em> Values emitted by this observable are <b>mutable</b> and owned by the host
   * {@code TextView} and thus are <b>not safe</b> to cache or delay reading (such as by observing
   * on a different thread). If you want to cache or delay reading the items emitted then you must
   * map values through a function which calls {@link String#valueOf} or
   * {@link CharSequence#toString() .toString()} to create a copy.
   * <p>
   * <em>Warning:</em> The created observable keeps a strong reference to {@code view}. Unsubscribe
   * to free this reference.
   * <p>
   * <em>Note:</em> A value will be emitted immediately on subscribe.
   */
  @CheckResult
  @NonNull
  public static Observable<CharSequence> textChanges(@NonNull TextView view) {
    EcomUtil.printLog("textChanges searching ");

    return Observable.create(new TextViewTextOnSubscribe(view));
  }
}
