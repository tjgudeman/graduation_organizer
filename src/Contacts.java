
public class Contacts
{
	private String firstName, lastName, home, inviteSymbol, giftSymbol, thankedSymbol;
	private boolean invited, gift, thanked;
	int count;
	
	public Contacts()
	{
		firstName = " ";
		lastName = "  ";
		home = "  ";
		invited = false;
		gift = false;
		thanked = false;
	}

	public Contacts( String fn, String ln, String h, boolean invite, boolean g, boolean th)
	{
		firstName =fn;
		lastName = ln;
		home = h;
		invited = invite;
		gift = g;
		thanked = th;
	}
	
	//setters
	public void setFirstName(String fn)
	{
		firstName = fn;
	}
	public void setLastName(String ln)
	{
		lastName = ln;
	}
	public void setHome(String h)
	{
		home = h;
	}
	public void setInvited(boolean invite)
	{
		invited = invite;
	}
	public void setGift(boolean g)
	{
		gift = g;
	}
	public void setThanked(boolean th)
	{
		thanked = th;
	}
	
	//Getters
	public String getFirstName()
	{
		return firstName;
	}
	public String getLastName()
	{
		return lastName;
	}
	public String getHome()
	{
		return home;
	}
	public boolean getInvited()
	{
		return invited;
	}
	public boolean getGift()
	{
		return gift;
	}
	public boolean getThanked()
	{
		return thanked;
	}
	
//ToString
	public String toString()
	{
		
		if ( invited == true)
		{
			inviteSymbol = "  X       ";	
		}
		else
			inviteSymbol = "        ";
		
		if ( gift == true)
		{
			giftSymbol = "  X       ";
		}
		else
			giftSymbol = "         ";
		if ( thanked == true)
		{
			thankedSymbol = "  X        ";
		}
		else
			thankedSymbol = "        ";
		
		
		return GradOrganizer.padTo(firstName, 15) + GradOrganizer.padTo(lastName, 15) + GradOrganizer.padTo(home, 20) + GradOrganizer.padTo(inviteSymbol, 10) + GradOrganizer.padTo(giftSymbol,10) + GradOrganizer.padTo(thankedSymbol, 10);
	}
}









