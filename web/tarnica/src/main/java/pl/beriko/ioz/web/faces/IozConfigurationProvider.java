package pl.beriko.ioz.web.faces;

import javax.servlet.ServletContext;

import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.config.Invoke;
import org.ocpsoft.rewrite.el.El;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.rule.Join;

public class IozConfigurationProvider extends HttpConfigurationProvider {

	@Override
	public Configuration getConfiguration(final ServletContext context) {
		return ConfigurationBuilder.begin().addRule(Join.path("/home").to("/pages/index.xhtml")).addRule(Join.path("/register").to("/pages/register.xhtml"))
				.addRule().when(Direction.isInbound().and(Path.matches("/logout"))).perform(Invoke.binding(El.retrievalMethod("#{authController.logout}")).and(Redirect.temporary(context.getContextPath() + "/")))	
				;
	}

	@Override
	public int priority() {
		// TODO Auto-generated method stub
		return 10;
	}

}
