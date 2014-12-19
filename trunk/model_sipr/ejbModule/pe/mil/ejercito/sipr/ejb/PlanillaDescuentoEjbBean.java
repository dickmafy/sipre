package pe.mil.ejercito.sipr.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PlanillaDescuentoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuento;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuentoLey;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuentoLeyPK;
import pe.mil.ejercito.sipr.model.SipreConceptoIngreso;
import pe.mil.ejercito.sipr.model.SipreDescuentoLey;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyDet;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyDetPK;
import pe.mil.ejercito.sipr.model.SipreEntidadCrediticia;
import pe.mil.ejercito.sipr.model.SiprePlanillaDescuento;
import pe.mil.ejercito.sipr.model.SiprePlanillaDescuentoPK;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetallePK;
import pe.mil.ejercito.sipr.model.SipreTipoPlanilla;
 
@Stateless
public class PlanillaDescuentoEjbBean extends GenericDAOImpl<SiprePlanillaDescuento>
		implements PlanillaDescuentoEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager em;

	@Override
	public List<SiprePlanillaDescuento> getListPlanillaDescuento(String mesproceso,Integer tipo) {
		try{
			StringBuilder sb=new StringBuilder();
			List<SiprePlanillaDescuento> lstRetorno=new ArrayList<SiprePlanillaDescuento>();
			sb.append("SELECT CPERSONA_NRO_ADM , ");
			sb.append(" CPLANILLA_MES_PROCESO, ");
			sb.append(" NPLANILLA_NUM_PROCESO, ");
			sb.append(" NPD_COD_SEC, ");
			sb.append(" NPD_MTO_EMPLEADO, ");
			sb.append(" NPD_MTO_EMPLEADOR, ");
			sb.append(" NPD_NUM_CUOTA, ");
			sb.append(" CPD_NRO_CHEQUE, ");
			sb.append(" NPD_MTO_DESCONTADO, ");
			sb.append(" NPD_NUM_TOT_CUOTA, ");
			sb.append(" CDL_CODIGO, ");
			sb.append(" CDLD_CODIGO, ");
			sb.append(" CCD_CODIGO ,");
			sb.append(" CTP_CODIGO, ");
			sb.append(" CEC_CODIGO ");
			sb.append(" FROM SIPRE_PLANILLA_DESCUENTO WHERE 1=1 ");
			if(mesproceso!=null && !mesproceso.isEmpty()){
			  sb.append(" and CPLANILLA_MES_PROCESO = '"+mesproceso+"'");
			}
			sb.append(" order by CPERSONA_NRO_ADM,NPD_COD_SEC ");
			
			Query q=em.createNativeQuery(sb.toString());
			
			List<Object[]> listaObj = q.getResultList();
			if (listaObj.size() > 0) {
				lstRetorno = new ArrayList<SiprePlanillaDescuento>();
				
				for (Object[] obj : listaObj) {
					SiprePlanillaDescuento pl=new SiprePlanillaDescuento();
					SiprePlanillaDescuentoPK pk=new SiprePlanillaDescuentoPK();
					pk.setCpersonaNroAdm(obj[0] == null ? "" : (obj[0].toString()));
					pk.setCplanillaMesProceso(obj[1] == null ? "" : (obj[1].toString()));
					pk.setNplanillaNumProceso(obj[2] == null ? null :Short.parseShort(obj[2].toString()));
					pk.setNpdCodSec(obj[3] == null ? null : Short.valueOf(obj[3].toString()));
					pl.setSiprePlanillaDescuentoPK(pk);
					
					pl.setNpdMtoEmpleado(obj[4] == null ? null :new BigDecimal((obj[4].toString())));
					pl.setNpdMtoEmpleador(obj[5] == null ? null :new BigDecimal((obj[5].toString())));
					pl.setNpdNumCuota(obj[6] == null ? null :Short.valueOf((obj[6].toString())));
					pl.setCpdNroCheque(obj[7] == null ? "" : (obj[7].toString()));
					pl.setNpdMtoDescontado(obj[8] == null ? null :new BigDecimal((obj[8].toString())));
					pl.setNpdNumTotCuota(obj[9] == null ? null :Short.valueOf((obj[9].toString())));
					
					SipreConceptoDescuentoLey cdl=new SipreConceptoDescuentoLey();
					//cdl.setSipreConceptoDescuentoLeyPK(new SipreConceptoDescuentoLeyPK(cdldCodigo, cdlCodigo, ccdCodigo)); 
					SipreDescuentoLeyDet leyDet=new SipreDescuentoLeyDet();//cdld/cdl
					leyDet.setSipreDescuentoLeyDetPK(new SipreDescuentoLeyDetPK(obj[11] == null ? "" : (obj[11].toString()), obj[10] == null ? "" : (obj[10].toString())));
					///
					SipreConceptoDescuento cd=new SipreConceptoDescuento();
					cd.setCcdCodigo(obj[12] == null ? "" : (obj[12].toString()));
					/////
					cdl.setSipreDescuentoLeyDet(leyDet);
					cdl.setSipreConceptoDescuento(cd);
					pl.setSipreConceptoDescuentoLey(cdl);
					
					SipreTipoPlanilla tp=new SipreTipoPlanilla();
					tp.setCtpCodigo(obj[13] == null ? "" : (obj[13].toString()));
					pl.setSipreTipoPlanilla(tp);
					
					SipreEntidadCrediticia ent=new SipreEntidadCrediticia();
					ent.setCecCodigo(obj[14] == null ? "" : (obj[14].toString()));
					pl.setSipreEntidadCrediticia(ent);
					
					
					lstRetorno.add(pl);
				}
			}
			//if(mesProceso!=null && !mesProceso.isEmpty()){
			//	q.setParameter("mes",mesProceso.trim());
		//	}
			//q.setParameter("dd","123632600");
			
			System.out.println(lstRetorno.size());
			return lstRetorno;
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	

	
}
