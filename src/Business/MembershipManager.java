package Business;

import java.util.List;

import Data.*;

/**
 * This class implements the MemberShip Manager controller
 * @author Bansal Ankur
 *	@version 1.0
 * @since 6th November 2014
 */
public class MembershipManager 
{

	/**
	 * This function retrieves list of membership the system would accept.
	 * @return A list of membership.
	 */
	public List onStartUp() 
	{
		// TODO - implement MembershipManager.onStartUp
		
		MembershipDAO membershipDAO = new MembershipDAO();
		
		List listOfMembership = membershipDAO.retrieveAll();
		
		return listOfMembership;
	}

}