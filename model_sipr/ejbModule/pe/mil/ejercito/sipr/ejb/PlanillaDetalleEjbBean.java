package pe.mil.ejercito.sipr.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PlanillaDetalleEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoIngreso;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetalle;
import pe.mil.ejercito.sipr.model.SiprePlanillaDetallePK;
import pe.mil.ejercito.sipr.model.SipreTipoPlanilla;

@Stateless
public class PlanillaDetalleEjbBean extends GenericDAOImpl<SiprePlanillaDetalle> implements PlanillaDetalleEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	/**
	 * El sueldo de la persona, por todos los Conceptos de ingreso que tiene.
	 */
	@Override
	public BigDecimal getSueldoPorPersona(String cpersonaNroAdm, Integer nplanillaNumProceso, String cplanillaMesProceso) {

		StringBuilder sb = new StringBuilder();
		/*sb.append(" SELECT o.siprePlanillaDetallePK.ctpCodigo ");
		sb.append(" , o.siprePlanillaDetallePK.cpersonaNroAdm ");
		sb.append(" , o.siprePlanillaDetallePK.cplanillaMesProceso ");
		sb.append(" , o.siprePlanillaDetallePK.nplanillaNumProceso ");
		*/
		sb.append(" SELECT ");
		sb.append(" SUM(o.npdMtoConcepto) ");
		sb.append(" FROM SiprePlanillaDetalle o ");

		sb.append(" WHERE  o.siprePlanillaDetallePK.nplanillaNumProceso=:nplanillaNumProceso  ");
		sb.append(" and  o.siprePlanillaDetallePK.cplanillaMesProceso=:cplanillaMesProceso  ");
		sb.append(" and  o.siprePlanillaDetallePK.ctpCodigo=:ctpCodigo ");
		sb.append(" and  o.siprePlanillaDetallePK.cpersonaNroAdm=:cpersonaNroAdm ");
		//sb.append(" and  o.siprePlanillaDetallePK.cpersonaNroAdm=:cciCodigo ");

		sb.append(" GROUP BY o.siprePlanillaDetallePK.ctpCodigo ");
		sb.append(" ,o.siprePlanillaDetallePK.cpersonaNroAdm ");
		sb.append(" ,o.siprePlanillaDetallePK.cplanillaMesProceso ");
		sb.append(" ,o.siprePlanillaDetallePK.nplanillaNumProceso ");
		//sb.append(" ,o.siprePlanillaDetallePK.cciCodigo ");
		Query q = em.createQuery(sb.toString());
		// situacion administrativa
		// q.setParameter("ctpCodigo", ctpCodigo); /
		q.setParameter("cpersonaNroAdm", cpersonaNroAdm);
		q.setParameter("nplanillaNumProceso", nplanillaNumProceso);
		q.setParameter("cplanillaMesProceso", cplanillaMesProceso);
		//q.setParameter("cciCodigo", cciCodigo);
		q.setParameter("ctpCodigo", "01");
		q.getResultList();
		BigDecimal resultado = (BigDecimal) q.getSingleResult();
		return resultado;
	}

	@Override
	public List<SiprePlanillaDetalle> getListPlanillaDetalle(String mesproceso) {
		try{
			StringBuilder sb=new StringBuilder();
			List<SiprePlanillaDetalle> lstRetorno=new ArrayList<SiprePlanillaDetalle>();
			sb.append("SELECT PD.CPERSONA_NRO_ADM , ");
			sb.append(" PD.CPLANILLA_MES_PROCESO, ");
			sb.append(" PD.NPLANILLA_NUM_PROCESO, ");
			sb.append(" PD.IND_PROCESO, ");
			sb.append(" PD.NPD_MTO_CONCEPTO, ");
			sb.append(" PD.CTP_CODIGO, ");
			sb.append(" PD.CCI_CODIGO, ");
			sb.append(" PD.CPD_CON_DESTINO, ");
			sb.append(" (SELECT Tp.Ctp_Ind_Afe_Neto FROM SIPRE_TIPO_PLANILLA TP WHERE Tp.Ctp_Codigo=PD.CTP_CODIGO) AS AFECTO ");
			sb.append(" FROM SIPRE_PLANILLA_DETALLE PD WHERE 1=1 ");
			if(mesproceso!=null && !mesproceso.isEmpty()){
			  sb.append(" and PD.CPLANILLA_MES_PROCESO = '"+mesproceso+"'");
			}
			sb.append(" order by PD.CPERSONA_NRO_ADM ");
			
			Query q=em.createNativeQuery(sb.toString());
			System.out.println("DETALLE::=>"+sb.toString());
			List<Object[]> listaObj = q.getResultList();
			if (listaObj.size() > 0) {
				lstRetorno = new ArrayList<SiprePlanillaDetalle>();
				
				for (Object[] obj : listaObj) {
					SiprePlanillaDetalle pl=new SiprePlanillaDetalle();
					SiprePlanillaDetallePK pk=new SiprePlanillaDetallePK();
					pk.setCpersonaNroAdm(obj[0] == null ? "" : (obj[0].toString()));
					pk.setCplanillaMesProceso(obj[1] == null ? "" : (obj[1].toString()));
					pk.setNplanillaNumProceso(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					pl.setSiprePlanillaDetallePK(pk);
					pl.setIndProceso(obj[3] == null ? "" : (obj[3].toString()));
					pl.setNpdMtoConcepto(obj[4] == null ? null :new BigDecimal((obj[4].toString())));
					
					SipreTipoPlanilla tipo=new SipreTipoPlanilla();
					tipo.setCtpCodigo(obj[5] == null ? "" : (obj[5].toString()));
					tipo.setCtpIndAfeNeto(obj[8] == null ? "" :(obj[8].toString()));
					pl.setSipreTipoPlanilla(tipo);
					
					SipreConceptoIngreso concepto=new SipreConceptoIngreso();
					concepto.setCciCodigo(obj[6] == null ? "" : (obj[6].toString()));
					concepto.setCciCodDestino(obj[7] == null ? "" : (obj[7].toString()));
					pl.setSipreConceptoIngreso(concepto);
					
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

	@Override
	public void deleteProcesoDelMes(String clase, String nombreCampoMes, String nombreCampoNumero, String cplanillaMesProceso,
			Integer nplanillaNumProceso) {
		StringBuilder sb = new StringBuilder();
		sb.append("delete from " + clase + " o ");
		sb.append(" where o." + nombreCampoMes + "=:cplanillaMesProceso");
		sb.append(" and o." + nombreCampoNumero + "=:nplanillaNumProceso");
		Query q = em.createQuery(sb.toString());
		q.setParameter("cplanillaMesProceso", cplanillaMesProceso);
		q.setParameter("nplanillaNumProceso", nplanillaNumProceso);
		q.executeUpdate();

	}

}
