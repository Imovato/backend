# Transacional Service

This module exposes transactional endpoints, including booking invite creation.

## Booking invite endpoint

- Method: `POST`
- Path: `/bookings/{bookingId}/invites`
- Auth: Bearer Token

Request body:

```json
{
  "guestId": "abc123"
}
```

Example response:

```json
{
  "inviteId": "inv001",
  "bookingId": "book001",
  "guestId": "abc123",
  "status": "PENDING",
  "shareAmount": 500.00,
  "deadline": "2026-03-17T00:00:00Z"
}
```

## Invite response endpoint

- Method: `PATCH`
- Path: `/invites/{inviteId}/respond`
- Auth: Bearer Token

Request body:

```json
{
  "action": "ACCEPTED"
}
```

Example response:

```json
{
  "inviteId": "inv001",
  "status": "ACCEPTED"
}
```

## Run tests

```bash
mvn test
```

## Pending invites endpoint

- Method: `GET`
- Path: `/invites/pending`
- Auth: Bearer Token

Example response:

```json
[
  {
    "inviteId": "inv001",
    "bookingId": "book001",
    "propertyTitle": "Coliving Centro SP",
    "propertyAddress": "Rua das Flores, 123 - Sao Paulo - SP",
    "hostName": "Host não informado",
    "shareAmount": 500.00,
    "totalAmount": 1500.00,
    "totalParticipants": 3,
    "checkInDate": "2026-04-01T00:00:00",
    "checkOutDate": "2026-05-01T00:00:00",
    "deadline": "2026-03-17T00:00:00",
    "status": "PENDING"
  }
]
```
