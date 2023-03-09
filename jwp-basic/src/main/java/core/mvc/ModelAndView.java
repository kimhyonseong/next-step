package core.mvc;

import core.mvc.view.View;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
  private View view;
  private Map<String, Object> model = new HashMap<>();

  public ModelAndView(View view) {
    this.view = view;
  }

  public ModelAndView addObject(String attributeName, Object attributeValue) {
    model.put(attributeName, attributeValue);
    return this;
  }

  public View getView() {
    return view;
  }

  public Map<String, Object> getModel() {
    return Collections.unmodifiableMap(model);
  }
}
