<SwiftMessage>
    <ApplicationHeader>
        <Sender>${senderBank}</Sender>
        <MessageType>300</MessageType>
        <Receiver>${receiverBank}</Receiver>
        <Reference>108</Reference>
    </ApplicationHeader>
    <MessageBody>
        <TradeDetails>
            <ReferenceNumber>${referenceNumber}</ReferenceNumber>
            <#if showOriRefNum>
            <OriginalReferenceNumber>${originalReferenceNumber}</OriginalReferenceNumber>
            </#if>
        </TradeDetails>
    </MessageBody>
</SwiftMessage>
