package pe.mil.ejercito.sipr.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.PlanillaEjbRemote;
import pe.mil.ejercito.sipr.model.SipreAgrupador;
import pe.mil.ejercito.sipr.model.SipreArma;
import pe.mil.ejercito.sipr.model.SipreBanco;
import pe.mil.ejercito.sipr.model.SipreCargo;
import pe.mil.ejercito.sipr.model.SipreCedula;
import pe.mil.ejercito.sipr.model.SipreEspecialidadAlterna;
import pe.mil.ejercito.sipr.model.SipreEstadoCivil;
import pe.mil.ejercito.sipr.model.SipreGrado;
import pe.mil.ejercito.sipr.model.SiprePlanilla;
import pe.mil.ejercito.sipr.model.SiprePlanillaPK;
import pe.mil.ejercito.sipr.model.SipreSituacionCausal;
import pe.mil.ejercito.sipr.model.SipreUnidad;

@Stateless
public class PlanillaEjbBean extends GenericDAOImpl<SiprePlanilla> implements PlanillaEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	
	@Override
	public Long siPersonaExisteEnPlanillaPrincipal(String cip) {
		Query q = em.createQuery("Select COUNT(o.id.cpersonaNroAdm) from SiprePlanilla o where o.id.cpersonaNroAdm=:cip");
		q.setParameter("cip", cip);
		Long count = (Long)q.getSingleResult();
		return count;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SiprePlanilla> getListPlanillaByNroAdm(String mesProceso) {
		try{
			List<SiprePlanilla> lstRetorno=new ArrayList<SiprePlanilla>();
			StringBuilder sb=new StringBuilder();
			//sb.append("Select p from SiprePlanilla p where 1=1 ");
			sb.append("SELECT CP.CPERSONA_NRO_ADM,CP.CPLANILLA_MES_PROCESO,CP.NPLANILLA_NUM_PROCESO,");
			sb.append("CP.DPLANILLA_FEC_FAL,CP.DPLANILLA_FEC_ING,CP.VPLANILLA_DOC_ALTA,");
			sb.append("CP.VPLANILLA_APE_NOM,CP.CPLANILLA_SEXO,CP.DPLANILLA_FEC_NAC,");
			sb.append("CP.NPLANILLA_NRO_HIJO,CP.CPLANILLA_COD_GRA_PEN,CP.CPLANILLA_IND_ONP,");
			sb.append("CP.NPLANILLA_POR_UNIF,CP.VPLANILLA_CAD_FUNC,CP.VPLANILLA_COD_ESSALUD,");
			sb.append("CP.VPLANILLA_CUSPP,CP.CPLANILLA_IND_AGUIN,CP.DPLANILLA_FEC_AFI_AFP,");
			sb.append("CP.DPLANILLA_FEC_FIN_CONTR,CP.DPLANILLA_FEC_PROMO,CP.NPLANILLA_RET_ASCENSO,");
			sb.append("CP.CPLANILLA_IND_LICENCIA,CP.DPLANILLA_FEC_RETIRO,CP.VPLANILLA_DOC_RETIRO,");
			sb.append("CP.CPLANILLA_SER_RECON,CP.VPLANILLA_DOC_RECON,CP.NPLANILLA_POR_PENSION,");
			sb.append("CP.CPLANILLA_SEX_PENSION,CP.VPLANILLA_NOM_CAUSANTE,CP.CGRADO_CODIGO,");
			sb.append("CP.CUNIDAD_CODIGO,CP.CSC_CODIGO,CP.CARMA_CODIGO,");
			sb.append("CP.CAGRUPADOR_CODIGO,CP.CCARGO_CODIGO,CP.CEA_CODIGO,");
			sb.append("CP.CEC_CODIGO,CP.CBANCO_CODIGO,CP.CSA_CODIGO,");
			sb.append("CP.CCEDULA_CODIGO,CP.CPLANILLA_DNI,CP.CPLANILLA_IND_QUI,");
			sb.append("CP.CPLANILLA_IND_ACT_PEN,CP.CPLANILLA_IND_CALCULO,CP.NPLANILLA_TIE_SERVICIO,");
			sb.append("(SELECT SU.VUNIDAD_DSC_CORTA FROM SIPRE_UNIDAD SU WHERE SU.CUNIDAD_CODIGO = Cp.Cunidad_Codigo  ) AS DESC_UNIDAD,");
			sb.append("(SELECT SU.VUNIDAD_DSC_GUAR FROM SIPRE_UNIDAD SU WHERE SU.CUNIDAD_CODIGO = Cp.Cunidad_Codigo  ) AS LUGAR,");
			sb.append("(SELECT SG.VGRADO_DSC_CORTA FROM SIPRE_GRADO SG WHERE SG.CGRADO_CODIGO = CP.CGRADO_CODIGO  ) AS DES_GRADO,");
			sb.append("(SELECT SB.VBANCO_DSC FROM SIPRE_BANCO SB WHERE SB.CBANCO_CODIGO = CP.CBANCO_CODIGO ) AS DES_BANCO ");
			sb.append(" FROM SIPRE_PLANILLA CP WHERE 1=1");
			
			
			if(mesProceso!=null && !mesProceso.isEmpty()){
				//sb.append(" and p.id.cplanillaMesProceso =:mes ");
				sb.append(" AND CP.CPLANILLA_MES_PROCESO = '"+mesProceso+"'");
			}
			//sb.append(" and p.id.cpersonaNroAdm =:dd ");
			sb.append(" order by CP.VPLANILLA_APE_NOM ");


			Query q=em.createNativeQuery(sb.toString());
			System.out.println(sb.toString());
			List<Object[]> listaObj = q.getResultList();
			if (listaObj.size() > 0) {
				lstRetorno = new ArrayList<SiprePlanilla>();
				
				for (Object[] obj : listaObj) {
					SiprePlanilla pl=new SiprePlanilla();
					SiprePlanillaPK pk=new SiprePlanillaPK();
					pk.setCpersonaNroAdm(obj[0] == null ? "" : (obj[0].toString()));
					pk.setCplanillaMesProceso(obj[1] == null ? "" : (obj[1].toString()));
					pk.setNplanillaNumProceso(obj[2] == null ? null :Integer.parseInt(obj[2].toString()));
					pl.setId(pk);
					pl.setDplanillaFecFal(obj[3] == null ? null :(java.util.Date)obj[3]);
					pl.setDplanillaFecIng(obj[4] == null ? null :(java.util.Date)obj[4]);
					pl.setVplanillaDocAlta(obj[5] == null ? "" : (obj[5].toString()));
					pl.setVplanillaApeNom(obj[6] == null ? "" : (obj[6].toString()));
					pl.setCplanillaSexo(obj[7] == null ? "" : (obj[7].toString()));
					pl.setDplanillaFecNac(obj[8] == null ? null :(java.util.Date)obj[8]);
					pl.setNplanillaNroHijo(obj[9] == null ? null : Integer.valueOf((obj[9].toString())));
					pl.setCplanillaCodGraPen(obj[10] == null ? "" : (obj[10].toString()));
					pl.setCplanillaIndOnp(obj[11] == null ? "" : (obj[11].toString()));
					pl.setNplanillaPorUnif(obj[12] == null ? null : new BigDecimal((obj[12].toString())));
					pl.setVplanillaCadFunc(obj[13] == null ? "" : (obj[13].toString()));
					pl.setVplanillaCodEssalud(obj[14] == null ? "" : (obj[14].toString()));
					pl.setVplanillaCuspp(obj[15] == null ? "" : (obj[15].toString()));
					pl.setCplanillaIndAguin(obj[16] == null ? "" : (obj[16].toString()));
					pl.setDplanillaFecAfiAfp(obj[17] == null ? null :(java.util.Date)obj[17]);
					pl.setDplanillaFecFinContr(obj[18] == null ? null :(java.util.Date)obj[18]);
					pl.setDplanillaFecPromo(obj[19] == null ? null :(java.util.Date)obj[19]);
					pl.setNplanillaRetAscenso(obj[20] == null ? null : Integer.valueOf((obj[20].toString())));
					pl.setCplanillaIndLicencia(obj[21] == null ? "" : (obj[21].toString()));
					pl.setDplanillaFecRetiro(obj[22] == null ? null :(java.util.Date)obj[22]);
					pl.setVplanillaDocRetiro(obj[23] == null ? "" : (obj[23].toString()));
					pl.setCplanillaSerRecon(obj[24] == null ? "" : (obj[24].toString()));
					pl.setVplanillaDocRecon(obj[25] == null ? "" : (obj[25].toString()));
					pl.setNplanillaPorPension(obj[26] == null ? null :new BigDecimal((obj[26].toString())));
					pl.setCplanillaSexPension(obj[27] == null ? "" : (obj[27].toString()));
					pl.setVplanillaNomCausante(obj[28] == null ? "" : (obj[28].toString()));
					
					
					SipreGrado grado=new SipreGrado();
					grado.setCgradoCodigo(obj[29] == null ? "" : (obj[29].toString()));
					grado.setVgradoDscCorta(obj[45] == null ? "" : (obj[45].toString()));
					pl.setSipreGrado(grado);
					
					SipreUnidad und=new SipreUnidad();
					und.setCunidadCodigo(obj[30] == null ? "" : (obj[30].toString()));
					und.setVunidadDscCorta(obj[46] == null ? "" : (obj[46].toString()));
					und.setVunidadDscGuar(obj[47] == null ? "" : (obj[47].toString()));
					pl.setSipreUnidad(und);
				
					
					SipreSituacionCausal causal=new SipreSituacionCausal();
					causal.setCscCodigo(obj[31] == null ? "" : (obj[31].toString()));
					pl.setSipreSituacionCausal(causal);
					
					SipreArma arma=new SipreArma();
					arma.setCarmaCodigo(obj[32] == null ? "" : (obj[32].toString()));
					pl.setSipreArma(arma);
					
					SipreAgrupador agrup=new SipreAgrupador();
					agrup.setCagrupadorCodigo(obj[33] == null ? "" : (obj[33].toString()));
					pl.setSipreAgrupador(agrup);
					
					
					SipreCargo cargo=new SipreCargo();
					cargo.setCcargoCodigo(obj[34] == null ? "" : (obj[34].toString()));
					pl.setSipreCargo(cargo);
					
					SipreEspecialidadAlterna esp=new SipreEspecialidadAlterna();
					esp.setCeaCodigo(obj[35] == null ? "" : (obj[35].toString()));
					pl.setSipreEspecialidadAlterna(esp);
					
					SipreEstadoCivil ec=new SipreEstadoCivil();
					ec.setCecCodigo(obj[36] == null ? "" : (obj[36].toString()));
					pl.setSipreEstadoCivil(ec);
					
					SipreBanco banco=new SipreBanco();
					banco.setCbancoCodigo(obj[37] == null ? "" : (obj[37].toString()));
					banco.setVbancoDsc(obj[48] == null ? "" : (obj[48].toString()));
					pl.setSipreBanco(banco);
					
					//CP.CSA_CODIGO administrativa38
					SipreCedula cel=new SipreCedula();
					cel.setCcedulaCodigo(obj[39] == null ? "" : (obj[39].toString()));
					pl.setSipreCedula(cel);
					
				   pl.setCplanillaDni(obj[40] == null ? "" : (obj[40].toString()));
				   pl.setCplanillaIndQui(obj[41] == null ? "" : (obj[41].toString()));
				 
					pl.setCplanillaIndActPen(obj[42] == null ? "" : (obj[42].toString()));
					pl.setCplanillaIndCalculo(obj[43] == null ? "" : (obj[43].toString()));
					pl.setNplanillaTieServicio(obj[44] == null ? "" : (obj[44].toString()));
					
					lstRetorno.add(pl);
				}
			}


			
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
