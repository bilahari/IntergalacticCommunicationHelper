package ich.regularExpressions;

import ich.planetSummary.PlanetSummary;
import ich.statements.Statements;

public interface RegularExpressions {

	public boolean isMatching(String stmt, PlanetSummary planetSummary);
	public void processStatement(Statements stmt, PlanetSummary planetSummary);
	public String getType();
	public void register();
	public String getPattern();
	public String getActualPattern(PlanetSummary planetSummary);
}
