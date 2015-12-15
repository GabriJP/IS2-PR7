package kata7.view;

import kata7.model.Histogram;

public interface HistogramDisplay {
    Histogram histogram();

    void show(Histogram histogram);
}
