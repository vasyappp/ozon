package Utils;

import Steps.BaseSteps;
import gherkin.formatter.model.Result;
import io.qameta.allure.cucumberjvm.AllureCucumberJvm;

public class AllureReporter extends AllureCucumberJvm {
    @Override
    public void result(final Result result) {
        if (result.getStatus().equals("failed")) {
            BaseSteps.takeScreenshot();
        }

        super.result(result);
    }
}
