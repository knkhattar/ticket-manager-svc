package com.kkcom.tm.ticket.svc;

import com.kkcom.tm.ticket.model.Ticket;

public interface TicketService {
	
	void addTicket();
	Ticket getTicket();
	void getTickets();

}
