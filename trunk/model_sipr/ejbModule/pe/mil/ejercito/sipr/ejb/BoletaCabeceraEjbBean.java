package pe.mil.ejercito.sipr.ejb;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pe.mil.ejercito.sipr.ejbremote.BoletaCabeceraEjbRemote;
import pe.mil.ejercito.sipr.model.SipreBoletaCabecera;

@Stateless
public class BoletaCabeceraEjbBean extends
		GenericDAOImpl<SipreBoletaCabecera> implements
		BoletaCabeceraEjbRemote {

	@PersistenceContext(unitName = "model_sipre")
	EntityManager	em;

	@Override
	
	public SipreBoletaCabecera saveCabecera(SipreBoletaCabecera boleta) {
		try{
			StringBuilder sb=new StringBuilder();
			
			sb.append("INSERT INTO Sipre_Boleta_Cabecera ");
			sb.append(" (CBC_MES_PROCESO,CBC_NRO_ADM,NBC_NUM_PROCESO,");
			sb.append(" CBC_COD_GRA_EFEC,VBC_DES_GRA_EFEC,CBC_COD_GRA_PENS, ");
			sb.append(" VBC_DES_GRA_PENS,CBC_COD_UNIDAD,VBC_DES_UNIDAD, ");
			sb.append(" TCB_TIP_PERSONA,NBC_NUM_BOLETA,NBC_MTO_INGRESO,");
			sb.append("  NBC_MTO_EGRESO,CBC_IND_ACT_PENS,VBC_LUGAR,");
			sb.append("  CBC_DNI,VBC_DES_BANCO,VBC_REG_REMUN,");
			sb.append(" VBC_REG_PENS,CUSUARIO_CODIGO,DBC_FEC_REG ) ");
			sb.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			
			Query q=em.createNativeQuery(sb.toString());
			q.setParameter(1, boleta.getSipreBoletaCabeceraPK().getCbcMesProceso());
			q.setParameter(2, boleta.getSipreBoletaCabeceraPK().getCbcNroAdm());
			q.setParameter(3, boleta.getSipreBoletaCabeceraPK().getNbcNumProceso());
			q.setParameter(4, boleta.getCbcCodGraEfec());
			q.setParameter(5, boleta.getVbcDesGraEfec());
			q.setParameter(6, boleta.getCbcCodGraPens());
			q.setParameter(7, boleta.getVbcDesGraPens());
			q.setParameter(8, boleta.getCbcCodUnidad());
			q.setParameter(9, boleta.getVbcDesUnidad());
			q.setParameter(10,boleta.getTcbTipPersona());
			q.setParameter(11,boleta.getNbcNumBoleta());
			q.setParameter(12,boleta.getNbcMtoIngreso());
			q.setParameter(13, boleta.getNbcMtoEgreso());
			q.setParameter(14, boleta.getCbcIndActPens());
			q.setParameter(15, boleta.getVbcLugar());
			q.setParameter(16, boleta.getCbcDni());
			q.setParameter(17, boleta.getVbcDesBanco());
			q.setParameter(18, boleta.getVbcRegRemun());
			q.setParameter(19, boleta.getVbcRegPens());
			q.setParameter(20, 1);
			q.setParameter(21, new Date());
		
			q.executeUpdate();
			return boleta;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	

	
}
