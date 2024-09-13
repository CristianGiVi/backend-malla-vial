package controllers;

import play.mvc.*;

public class HomeController extends Controller {

    public Result index() {
        return ok("Hola ");
    }

        public Result index2(Long id){
        return ok("Hola " + id);
    }

}
