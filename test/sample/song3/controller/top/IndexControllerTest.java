package sample.song3.controller.top;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;

import sample.smog3.controller.top.IndexController;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IndexControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/top/");
        IndexController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/top/index.jsp"));
    }
}
