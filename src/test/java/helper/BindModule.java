package helper;
import com.google.inject.AbstractModule;
import service.GoRest;

public final class BindModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(GoRest.class).to(GoRest.class);
            bind(ConfigHelper.class).to(ConfigHelper.class);
            bind(CsvHelper.class).to(CsvHelper.class);
        }
    }
