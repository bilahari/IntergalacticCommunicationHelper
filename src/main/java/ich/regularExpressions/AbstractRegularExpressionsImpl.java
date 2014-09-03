package ich.regularExpressions;

import javax.annotation.PostConstruct;

public abstract class AbstractRegularExpressionsImpl implements RegularExpressions{

	@PostConstruct
	public void register(){
		RegExConfig.addRegularExpression(getClass().getName());
	}
}
