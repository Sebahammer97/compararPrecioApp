package procesado;

import java.util.ArrayList;

import modelo.Local;

public class ResultadoPosAnalisis 
{
	private String decision;
	private ArrayList<Local> locales;
	
	public ResultadoPosAnalisis(ArrayList<CompraDecision> arreglo)
	{
		decision = "";
		locales = new ArrayList<Local>();
		float total = 0;
		for(CompraDecision item: arreglo)
		{
			Local local = item.getLocalDecidido();
			for(Local l: locales)
			{
				if(l.getId() == local.getId())
				{
					local = null;
					break;
				}
			}
			if(local != null)
			{
				locales.add(item.getLocalDecidido());
			}
		}
		
		for(Local l: locales)
		{
			decision = decision.concat(l.presentarLocal());
			for(CompraDecision item: arreglo)
			{
				if(l.getId()==item.getLocalDecidido().getId())
				{
					decision = decision.concat(item.contarDecision());
					total += item.getPrecioPrevisto();
				}
			}
			decision = decision.concat("\n");
		}
		decision = decision.concat("Gasto total Previsto: "+total+"\n");
	}

	public String getDecision()
	{
		return decision;
	}

	public ArrayList<Local> getLocales()
	{
		return locales;
	}
	
	
}
