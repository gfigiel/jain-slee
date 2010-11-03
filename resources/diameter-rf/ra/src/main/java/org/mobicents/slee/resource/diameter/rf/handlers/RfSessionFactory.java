/*
 * JBoss, Home of Professional Open Source
 * 
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a full listing
 * of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU General Public License, v. 2.0.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * v. 2.0 along with this distribution; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
package org.mobicents.slee.resource.diameter.rf.handlers;

import org.jdiameter.api.IllegalDiameterStateException;
import org.jdiameter.api.InternalException;
import org.jdiameter.api.OverloadException;
import org.jdiameter.api.RouteException;
import org.jdiameter.api.SessionFactory;
import org.jdiameter.api.rf.ClientRfSession;
import org.jdiameter.api.rf.ServerRfSession;
import org.jdiameter.api.rf.events.RfAccountingAnswer;
import org.jdiameter.api.rf.events.RfAccountingRequest;
import org.jdiameter.common.impl.app.rf.RfSessionFactoryImpl;
import org.mobicents.slee.resource.diameter.base.handlers.DiameterRAInterface;

/**
 * 
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
public class RfSessionFactory extends RfSessionFactoryImpl {

  public DiameterRAInterface ra;

  /**
   * 
   */
  public RfSessionFactory() {
    // TODO Auto-generated constructor stub
  }

  /**
   * @param sessionFactory
   */
  public RfSessionFactory(DiameterRAInterface ra, SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public void doRfAccountingAnswerEvent(ClientRfSession appSession, RfAccountingRequest acr, RfAccountingAnswer aca) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException {
    ra.fireEvent(appSession.getSessions().get(0).getSessionId(), aca.getMessage());
  }

  @Override
  public void doRfAccountingRequestEvent(ServerRfSession appSession, RfAccountingRequest acr) throws InternalException, IllegalDiameterStateException, RouteException, OverloadException {
    ra.fireEvent(appSession.getSessions().get(0).getSessionId(), acr.getMessage());
  }

}