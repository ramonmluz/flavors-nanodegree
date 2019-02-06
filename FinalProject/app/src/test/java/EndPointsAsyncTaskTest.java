import android.content.Context;
import android.support.v4.util.Pair;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class EndPointsAsyncTaskTest {

    public static final String JOKE_UDACITY = "JOKE UDACITY";

    final CountDownLatch signal = new CountDownLatch(1);

    @Test
    public void testAsync() throws Throwable {
        final EndPointsAsyncTask task = new EndPointsAsyncTask(JOKE_UDACITY) {
            @Override
            protected String doInBackground(Pair<Context, EndPointResultCallback>... params) {
                return JOKE_UDACITY;
            }

            @Override
            protected void onPostExecute(String result) {
               Assert.assertNotNull(result);
               Assert.assertFalse(result.equals(""));
               signal.countDown();
            }
        };

        Runnable runTest = new Runnable() {
            @Override
            public void run() {
                task.execute();
            }
        };

        runTest.run();

        signal.await(30, TimeUnit.SECONDS);
    }
}