package sample.smog3.controller.top;

import org.smog3.annotation.Form;

import sample.smog3.bean.Parameters;
import sample.smog3.common.controller.Controller;

/**
 * Top���ʏ����y�[�W
 * 
 * @author abemasafumi
 * 
 */
@Form("IndexForm")
public class IndexController extends Controller {

    @Override
    protected String run(Parameters params) {

        return "index.html";
    }

}
