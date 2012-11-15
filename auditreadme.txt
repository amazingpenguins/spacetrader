Audit Failure:
    Numeric Literals.

Reasoning:
    Most of these failures are from initializing interface objects and throwaway
    initializations. For example, for CardLayout, the Market has to be 
    instantiated, even though it is not on a planet. So, random values were
    given.

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
	"Casting from ""int"" to ""short"""	 Either test the value first or attempt to remove the cast.
	    Medium Severity InitView.java	Line 298
	"Casting from ""int"" to ""short"""	 Either test the value first or attempt to remove the cast.
    	Medium Severity Market.java	Line 101
	"Casting from ""int"" to ""short"""	 Either test the value first or attempt to remove the cast.
    	Medium Severity UniversePanel.java	Line 150
	"Casting from ""double"" to ""int""" Either test the value first or attempt to remove the cast.
    	Medium Severity UniversePanel.java	Line 209

Reasoning:
    These casts are done in order to maintin data types that are not memory
    wastes. The stats of the player are a maximum of 16, so using an int that
    is typically >= 32 bits is overkill.


Audit Failure:
    Audit violations for edu.gatech.amazingpenguins.spacetrader at 11/15/12 10:03 AM

    Description,Recommendations,Severity,Resource,Line
    Cyclomatic Complexity (6)
	Method is too complex (cyclomatic complexity = 34)	Reduce the complexity of the method by extracting 
	    some of the code into a separate method.	Medium Severity	Market.java	Line 190
	Method is too complex (cyclomatic complexity = 11)	Reduce the complexity of the method by extracting 
	    some of the code into a separate method.	Medium Severity	MarketPanel.java	Line 232
	Method is too complex (cyclomatic complexity = 13)	Reduce the complexity of the method by extracting 
	    some of the code into a separate method.	Medium Severity	Planet.java	Line 309
	Method is too complex (cyclomatic complexity = 17)	Reduce the complexity of the method by extracting 
	    some of the code into a separate method.	Medium Severity	SolarSystem.java	Line 249
	Constructor is too complex (cyclomatic complexity = 11)	Reduce the complexity of the constructor by 
	    extracting some of the code into a separate method.	Medium Severity	TradeGood.java	Line 89
	Method is too complex (cyclomatic complexity = 11)	Reduce the complexity of the method by extracting   
	    some of the code into a separate method.	Medium Severity	TradeGood.java	Line 171

Reasoning:
    Any time there is a switch statement, this error occurs, even when the methd
    is not "overly" complex. In most of these methods, there is simply no other
    high performance/convenient way of processing the params.


Audit Failure:

Reasoning:

