package com.app.delivxstore.utility;

import com.amazonaws.auth.AWSAbstractCognitoDeveloperIdentityProvider;
import com.amazonaws.regions.Regions;

public class IdentityProvider extends AWSAbstractCognitoDeveloperIdentityProvider {

  private static final String PROVIDER = IdentityProvider.class.getSimpleName();

  public IdentityProvider(String accountId, String identityPoolId, Regions region) {
    super(accountId, identityPoolId, region);
  }

  // Return the developer provider name which you choose while setting up the
  // identity pool in the &COG; Console

  @Override
  public String getProviderName() {
    return PROVIDER;
  }

  // Use the refresh method to communicate with your backend to get an
  // identityId and token.
  //
  //  @Override
  //  public String refresh() {
  //
  //    // Override the existing token
  //    setToken(null);
  //
  //    // Get the identityId and token by making a call to your backend
  //    // (Call to your backend)
  //
  //    // Call the update method with updated identityId and token to make sure
  //    // these are ready to be used from Credentials Provider.
  //    updateCredentials();
  //    update(identityId, token);
  //    return token;
  //
  //  }
  //
  //  // If the app has a valid identityId return it, otherwise get a valid
  //  // identityId from your backend.
  //
  //  @Override
  //  public String getIdentityId() {
  //
  ////    // Load the identityId from the cache
  ////    identityId = cachedIdentityId;
  ////
  //    if (identityId == null) {
  //      // Call to your backend
  //
  //      updateCredentials();
  //    } else {
  //      return identityId;
  //    }
  //    return identityId;
  //  }


  public void updateCredentials(String cognitoId,String cognitoToken) {
    this.identityId = cognitoId;
    this.token = cognitoToken;
  }
}