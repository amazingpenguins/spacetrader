
Audit Failure:
    Audit violations for edu.gatech.amazingpenguins.spacetrader at 11/15/12 9:02 AM

    Description,Recommendations,Severity,Resource,Line
    Obey General Contract of Equals (1)
	    Missing identity check	Add a test for object identity.	Medium Severity	TradeGood.java	Line

Reasoning:
    This method does properly check the type of the Object and it does provide
    support for all Object types. After trying many different things to
    satisfy this test, it errors no matter how the check is done.

Audit Failure:
    Audit violations for edu.gatech.amazingpenguins.spacetrader at 11/15/12 9:02 AM

    Loss of Precision in Cast (4)
	"Casting from ""int"" to ""short"""	 Either test the value first or attempt to remove the cast.	Medium Severity
	    InitView.java	Line 298
	"Casting from ""int"" to ""short"""	 Either test the value first or attempt to remove the cast.	Medium Severity
	    Market.java	Line 101
	"Casting from ""int"" to ""short"""	 Either test the value first or attempt to remove the cast.	Medium Severity
	    UniversePanel.java	Line 150
	"Casting from ""double"" to ""int""" Either test the value first or attempt to remove the cast.	Medium Severity
	    UniversePanel.java	Line 209

Reasoning:
    These casts are done in order to maintin data types that are not memory
    wastes. The stats of the player are a maximum of 16, so using an int that
    is typically >= 32 bits is overkill.

Audit Failure:
