package com.leavemanagement.client.view;

import com.google.gwt.dom.client.Style.Unit;

import gwt.material.design.client.ui.MaterialColumn;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialRow;

public class AdminRowHeading extends MaterialRow {

	public AdminRowHeading() {
		MaterialLabel lblName = new MaterialLabel("Name");
		MaterialLabel lblType = new MaterialLabel("Type");
		MaterialLabel lblFrom = new MaterialLabel("From");
		MaterialLabel lblTo = new MaterialLabel("To");
		MaterialLabel lblDays = new MaterialLabel("Days");
		MaterialLabel lblDecision = new MaterialLabel("Decision");

		lblName.setWidth("120px");
		lblType.setWidth("120px");
		lblFrom.setWidth("140px");
		lblTo.setWidth("140px");
		lblDays.setWidth("120px");
		lblDecision.setWidth("120px");

		lblName.addStyleName("blueBold");
		lblType.addStyleName("blueBold");
		lblFrom.addStyleName("blueBold");
		lblTo.addStyleName("blueBold");
		lblDays.addStyleName("blueBold");
		lblDecision.addStyleName("blueBold");
		lblType.setFontSize(1.2, Unit.EM);
		lblName.setFontSize(1.2, Unit.EM);
		lblDays.setFontSize(1.2, Unit.EM);
		lblDecision.setFontSize(1.2, Unit.EM);
		lblFrom.setFontSize(1.2, Unit.EM);
		lblTo.setFontSize(1.2, Unit.EM);

		MaterialColumn colLblName = new MaterialColumn();
		colLblName.add(lblName);
		add(colLblName);
		MaterialColumn colLblType = new MaterialColumn();
		colLblType.add(lblType);
		add(colLblType);
		MaterialColumn colLblFrom = new MaterialColumn();
		colLblFrom.add(lblFrom);
		add(colLblFrom);
		MaterialColumn colLblTo = new MaterialColumn();
		colLblTo.add(lblTo);
		add(colLblTo);
		MaterialColumn colLblDays = new MaterialColumn();
		colLblDays.add(lblDays);
		add(colLblDays);
		MaterialColumn colLblDecision = new MaterialColumn();
		colLblDecision.add(lblDecision);
		add(colLblDecision);
	}

}
