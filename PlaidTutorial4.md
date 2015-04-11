# Introduction #

This tutorial explains how references can be annotated with access permissions in Plaid.

# Prerequisite #
PlaidTutorial1 , PlaidTutorial2 and PlaidTutorial3

# Motivation #

Consider the following code snippet

```
    Integer method readFromFile(ClosedFile f) {
        openHelper(f);
        val Integer x = computeBase() + f.read();
        f.close();
        x;
     } 
```

Now consider the case if an alias to _f_ is stored somewhere global variable. In that case it might happen that call to _computeBase_ might close the file. To solve this Plaid brings in the notion of access permissions. Access permissions describe how an object can be shared across aliases. For instance the above code snippet can be modified as follows to handle the challenge described

```
   Integer method readFromFile(unique ClosedFile f)
```

Here we have used the permission _unique_. It grants read/write access on the object and guarantee that no other reference has any access to the object.

## Permission system in Plaid ##
Plaid allows to have the following permissions on references:
  * **_unique_** _unique_ permissions grant read/write access and guarantee that no other references has any access to the object. _unique_ is the default permission for references.
  * **_immutable_** _immutable_ permissions grant read-only access to the referenced object and guarantee that no reference had read/write access to the same object.
  * **_full_** _full_ permissions grant read/write access to the reference object and guarantee that no other referencehas read/write access to the same object.
  * **_pure_** _pure_ permissions grant read-only access to the referenced object. But this permission doesn't prohibit other permissions to modify the object.

Thus it is clear that unique is the strongest permission that can be assigned to a reference.

## Permission splitting ##
Plaid allows splitting of the permissions among the references. For example consider the following code snippet
```
unique ClosedFile obj = new ClosedFile;
/* permission splits */
full ClosedFile fullRef = obj;
pure ClosedFile pureRef1 = obj;
pure ClosedFile pureRef2 = obj;
```

Following are some of the legal splits

  * unique => full & pure
  * full => full & pure or immutable and immutable
  * immutable => immutable & immutable

Now consider the following example where permission splitting can be handy
```

state JDBCResultSet{
  
   val Data data;

   method Boolean hasNext(){
	//Checks if end of the list is reached
   }

   method Data next(){
	// Returns the next element in the result set
   }

 }

 state Data{
	
     var value;
     method String getValue(){
     	value;
     }

     method setValue(String value){
	this.value = value;
     }

 }

state MainProgram{

 method main(){
     unique JDBCResultSet jdbcResultSet = this.getResultSet();
     immutable JDBCResultSet immutableResultSet1 = jdbcResultSet;
     immutable JDBCResultSet immutableResultSet2 = jdbcResultSet; 
 }

 method getResultSet(){
     //creates and Returns the jdbc result set 
 }

}
```
For simplicity here we have not shown the code for creation of Java JDBC result set. In this example we have created two immutable result sets. This guarantees the immutability of the result sets when the iteration is in progress.




