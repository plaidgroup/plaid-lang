package plaid.demo;

import plaid.runtime.PlaidMethod;
import plaid.runtime.PlaidObject;
import plaid.runtime.Util;

public class PlaidCellProxyFactory implements CellFactory<PlaidCellProxy>{

	@Override
	public PlaidCellProxy makeCell() {
//		PlaidState aliveCellState = (plaid.runtime.PlaidState) DeadCell.global$c0pe.lookup("DeadCell");
//		PlaidObject deadCell = aliveCellState.instantiate(new PlaidObject[0]);
		
		PlaidMethod makeStartCellMethod = plaid.demo.plugin.makeStartCell.makeStartCell_func;
		PlaidObject deadCell = Util.call(makeStartCellMethod, Util.unit());
		return new PlaidCellProxy(deadCell);
	}

}
